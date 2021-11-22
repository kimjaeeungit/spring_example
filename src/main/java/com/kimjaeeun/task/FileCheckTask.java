package com.kimjaeeun.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kimjaeeun.controller.UploadController;
import com.kimjaeeun.domain.AttachVo;
import com.kimjaeeun.mapper.AttachMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Component @Log4j @AllArgsConstructor
public class FileCheckTask {
	private AttachMapper attachMapper;
	private Set<String> getFolderBefore(List<AttachVo>fileList){
		//fileList를 stream화 
		//mapdp vo파라미터를 넣고 >>이부분이 리턴vo.getPath()).collect(Collectors.toList())); 
		//<AttachVo>가 겟패스만 있는정보로 스트링으로 바뀜
		//HashSet<>사용하면 중복이 사라짐
		return new HashSet<>(fileList.stream().map(vo->vo.getPath()).collect(Collectors.toList()));
	}
	
	@Scheduled(cron="0 0 2 * * *") //매일 두시에 작업 
	public void checkFiles(){
		log.warn("file check task run............");
		log.warn("================================");
		
		attachMapper.getOldFiles().forEach(log::info);
		//DB list
		List<AttachVo>fileList = attachMapper.getOldFiles();
		if(fileList == null)return;
		List<Path> fileListPaths= 
				fileList
				.stream()
				.map(
						vo -> 
						Paths.get(UploadController.getUploadFolder(),vo.getFullPath()))
				.collect(Collectors.toList());	
		
		//log.warn("========================");
		//fileListPaths.forEach(log::info);
	
		//thumbnail여부 확인후 예상파일목록에 섬네일 파일도 추가
		fileList
		.stream()
		.filter(vo->vo.isImage())
		.map(vo->Paths.get(UploadController.getUploadFolder(),vo.getThumb()))
		.forEach(filePath -> fileListPaths.add(filePath));
		//log.warn("========================");
		//fileListPaths.forEach(log::info);
		
		//이전폴더들에 들어있던 물리적 파일 list가져오기
		//log.warn("========================");
		//getFolderBefore(fileList).forEach(log::info);
		
		getFolderBefore(fileList).forEach(folder -> {
			File targetDir=Paths.get(UploadController.getUploadFolder(),folder).toFile();
			//log.warn("=================================");
			//log.warn(targetDir);
			File[] removeFiles = targetDir.listFiles(file->!fileListPaths.contains(file.toPath()));
			//db상에 존재하지 않고 서버상에 만 있는 파일들 (지워져야될 파일)
			Arrays.asList(removeFiles).forEach(file->file.delete());
		});
		
		//
	}
}
