package com.alkemy.ong.controller;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.dto.PageResponseDto;
import com.alkemy.ong.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
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
import javax.validation.Valid;
@Tag(name = "Member", description = "Endpoint to create, update, delete and get page of members")
@RestController
@RequestMapping(MEMBER_URL)
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    @Operation(summary = "Get all members")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully Operation"),
            @ApiResponse(responseCode = "401", description = "You do not have valid credentials"),
            @ApiResponse(responseCode = "403", description = "Forbidden this request"),
            @ApiResponse(responseCode = "404", description = "Resource is not available to the server")
    })
    public ResponseEntity<?> getMembers(@RequestParam(value = "page", defaultValue = "1") int page) {
        try {
            PageResponseDto pageResponse = memberService.getAll(page);
            return ResponseEntity.ok().body(pageResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a member by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully Operation"),
            @ApiResponse(responseCode = "401", description = "You do not have valid credentials"),
            @ApiResponse(responseCode = "403", description = "Forbidden this request"),
            @ApiResponse(responseCode = "404", description = "Resource is not available to the server")
    })
    public ResponseEntity<?> deleteMembers(@PathVariable(value = "id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            memberService.deleteById(id);
            response.put("message", "Member has been successfully deleted");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    @Operation(summary = "Create a member")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully Operation"),
            @ApiResponse(responseCode = "401", description = "You do not have valid credentials"),
            @ApiResponse(responseCode = "403", description = "Forbidden this request"),
            @ApiResponse(responseCode = "404", description = "Resource is not available to the server")
    })
    public ResponseEntity<?> createMember(@Valid @RequestBody MemberDto memberDto){
        try {
            memberService.save(memberDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new  ResponseEntity<> (HttpStatus.CONFLICT);
        }
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Update a member by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully Operation"),
            @ApiResponse(responseCode = "401", description = "You do not have valid credentials"),
            @ApiResponse(responseCode = "403", description = "Forbidden this request"),
            @ApiResponse(responseCode = "404", description = "Resource is not available to the server")
    })
    public ResponseEntity<?> updateMember(@PathVariable("id") Long id, @Valid @RequestBody MemberDto memberCreationDto)
    {
        try {
            memberService.updateMemberById(id, memberCreationDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}

