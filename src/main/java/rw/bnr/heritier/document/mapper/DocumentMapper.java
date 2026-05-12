package rw.bnr.heritier.document.mapper;

import org.springframework.stereotype.Component;
import rw.bnr.heritier.document.dto.DocumentResponseDto;
import rw.bnr.heritier.document.model.ApplicationDocument;

@Component
public class DocumentMapper {

    public DocumentResponseDto toResponse(
            ApplicationDocument document
    ) {

        return DocumentResponseDto.builder()
                .id(document.getId())
                .fileName(document.getOriginalFileName())
                .fileSize(document.getFileSize())
                .contentType(document.getContentType())
                .versionNumber(document.getVersion())
                .uploadedBy(
                        document.getUploadedBy().getEmail()
                )
                .uploadedAt(document.getUploadedAt())
                .build();
    }

}