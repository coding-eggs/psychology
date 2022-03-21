package com.lby.psychology.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PsycUploadVo {

    @ApiModelProperty("当前块的次序")
    private Long chunkNumber;

    @ApiModelProperty("文件被分成块的总数")
    private Integer totalChunks;

    @ApiModelProperty("分块大小")
    private Integer chunkSize;

    @ApiModelProperty("当前块的大小，实际大小")
    private Long currentChunkSize;

    @ApiModelProperty("文件总大小")
    private Long totalSize;

    @ApiModelProperty("文件的唯一标示")
    private String identifier;

    @ApiModelProperty("文件名")
    private String filename;

    @ApiModelProperty("文件夹上传的时候文件的相对路径属性")
    private String relativePath;

    @ApiModelProperty("上传的文件")
    private MultipartFile file;


}
