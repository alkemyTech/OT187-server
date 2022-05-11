package com.alkemy.ong.controller;

import com.alkemy.ong.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.alkemy.ong.utility.Constantes.MEMBER_URL;

@RestController
@RequestMapping(MEMBER_URL)
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Operation(summary = "Get a list if all members")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation. All members are returned",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MemberDto.class))}),
            @ApiResponse(responseCode = "400", description = "Page number is not valid",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<?> getMembers(){
        Map<String,Object> response=new HashMap<>();

        response.put("members",memberService.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Operation(summary = "Delete a member by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation. Member is deleted",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MemberDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid member id",
                    content = @Content)})
    @DeleteMapping( value = "/{id}")
    public ResponseEntity<?> deleteMembers(@PathVariable(value = "id")Long id){
        Map<String,Object> response=new HashMap<>();
        memberService.deleteById(id);
        response.put("mensaje","El miembro ha sido eliminado con exito");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
