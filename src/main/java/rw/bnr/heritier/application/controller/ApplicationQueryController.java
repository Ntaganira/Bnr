package rw.bnr.heritier.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import rw.bnr.heritier.application.dto.ApplicationResponseDto;
import rw.bnr.heritier.application.service.ApplicationQueryService;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@Tag(
        name = "Application Queries",
        description = "Application query management APIs"
)
public class ApplicationQueryController {

    private final ApplicationQueryService queryService;

    @GetMapping
    public Page<ApplicationResponseDto> getApplications(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size
    ) {

        return queryService.getApplications(
                PageRequest.of(page, size)
        );
    }

    @GetMapping("/{id}")
    public ApplicationResponseDto getApplication(
            @PathVariable Long id
    ) {

        return queryService.getApplicationById(id);
    }

}