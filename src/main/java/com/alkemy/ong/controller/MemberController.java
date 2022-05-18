package com.alkemy.ong.controller;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.dto.PageResponseDto;
import com.alkemy.ong.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.alkemy.ong.utility.Constantes.MEMBER_URL;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;

@RestController
@RequestMapping(MEMBER_URL)
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    @ApiOperation("Obtiene todos los miembros")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully Operation"),
            @ApiResponse(code = 401, message = "You do not have valid credentials"),
            @ApiResponse(code = 403, message = "Forbidden this request"),
            @ApiResponse(code = 404, message = "Resource is not available to the server")
    })
    public ResponseEntity<?> getMembers(@RequestParam(value = "page", defaultValue = "1") int page) {
        PageResponseDto pageResponse = memberService.getAll(page);
        return ResponseEntity.ok().body(pageResponse);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Elimina un miembro por el id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully Operation"),
            @ApiResponse(code = 401, message = "You do not have valid credentials"),
            @ApiResponse(code = 403, message = "Forbidden this request"),
            @ApiResponse(code = 404, message = "Resource is not available to the server")
        })
    public ResponseEntity<?> deleteMembers(@PathVariable(value = "id") Long id) {
        Map<String, Object> response = new HashMap<>();
        memberService.deleteById(id);
        response.put("message", "Member has been successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    @PostMapping
    @ApiOperation("Create a member")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully Operation"),
            @ApiResponse(code = 401, message = "You do not have valid credentials"),
            @ApiResponse(code = 403, message = "Forbidden this request"),
            @ApiResponse(code = 404, message = "Resource is not available to the server")
        })
    public ResponseEntity<?> createMember(@Valid @ModelAttribute(name = "memberDto") MemberDto memberDto){
        memberService.createMember(memberService.save(memberDto));
        try {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new  ResponseEntity<> (HttpStatus.CONFLICT);
        }
    }
    
    
    
    @PutMapping(path = "/{id}")
    @ApiOperation("update a member by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully Operation"),
            @ApiResponse(code = 401, message = "You do not have valid credentials"),
            @ApiResponse(code = 403, message = "Forbidden this request"),
            @ApiResponse(code = 404, message = "Resource is not available to the server")
        })
	public ResponseEntity<?> updateMember(@PathVariable("id") Long id, @Valid @ModelAttribute(name = "memberCreationDto") MemberDto memberCreationDto)
    {
           memberService.updateMemberById(id, memberCreationDto);
		try {
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}

