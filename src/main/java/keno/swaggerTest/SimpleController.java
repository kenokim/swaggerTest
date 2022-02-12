package keno.swaggerTest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.NoSuchElementException;

@RestController
@Tag(name = "Simple Controller new")
public class SimpleController {

    @Autowired MemberRepository memberRepository;

    @Operation(description = "Simple GET api endpoint")
    @GetMapping("/hello")
    public ResponseEntity helloRequest() {
        System.out.println("hello");
        return new ResponseEntity("ok", HttpStatus.OK);
    }

    @Operation(description = "회원 조회: /get?name=... returns response 200 or response 400")
    @GetMapping("/get")
    public Member getMember(@RequestParam String name) {
        Member m = memberRepository.findByName(name);
        if (m == null) throw new NoSuchElementException("not exist");
        System.out.println(m.getName());
        return m;
    }



    @PostMapping("/save")
    @Transactional
    public ResponseEntity saveMember(@RequestBody MemberFormDto memberFormDto) {
        System.out.println("new member added");
        Member m = memberRepository.findByName(memberFormDto.getName());
        if (m != null) return new ResponseEntity("Duplcate", HttpStatus.BAD_REQUEST);
        Member nm = Member.createMember(memberFormDto.getName());
        memberRepository.save(nm);
        return new ResponseEntity("ok", HttpStatus.OK);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity notExist(NoSuchElementException e) {
        return new ResponseEntity("not exist", HttpStatus.BAD_REQUEST);
    }

}
