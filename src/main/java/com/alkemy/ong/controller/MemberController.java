package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.PageResponseDto;
import com.alkemy.ong.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.alkemy.ong.utility.Constantes.MEMBER_URL;

@Tag(name = "Member")
@RestController
@RequestMapping(MEMBER_URL)
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Operation(summary = "Find all members")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All members found succesfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PageResponseDto.class)) }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Page doesn't exist or is out of range")
    })
    @GetMapping
    public ResponseEntity<?> getMembers(@RequestParam(value = "page", defaultValue = "1") int page) {
        PageResponseDto pageResponse = memberService.getAll(page);
        return ResponseEntity.ok().body(pageResponse);
    }

    @Operation(summary = "Delete a member")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Member deleted succesfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Member not found")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteMembers(@PathVariable(value = "id") Long id) {
        Map<String, Object> response = new HashMap<>();
        memberService.deleteById(id);
        response.put("mensaje", "Member has been successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

