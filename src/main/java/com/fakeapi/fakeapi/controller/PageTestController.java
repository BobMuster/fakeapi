/*
 * // Copyright (C) <2020> <becon GmbH>
 * //
 * // This program is free software: you can redistribute it and/or modify
 * // it under the terms of the GNU General Public License as published by
 * // the Free Software Foundation, version 3 of the License.
 * //
 * // This program is distributed in the hope that it will be useful,
 * // but WITHOUT ANY WARRANTY; without even the implied warranty of
 * // MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * // GNU General Public License for more details.
 * //
 * // You should have received a copy of the GNU General Public License
 * // along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.fakeapi.fakeapi.controller;

import com.fakeapi.fakeapi.dto.Session;
import com.fakeapi.fakeapi.dto.Token;
import com.fakeapi.fakeapi.dto.twitter.TwitterUser;
import com.fakeapi.fakeapi.service.CursorPage;
import com.fakeapi.fakeapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping(value = "/api/page")
public class PageTestController {

    private UserDetailsService userDetailsService;

    @GetMapping("/session")
    public ResponseEntity<?> getSessionId() throws Exception {

//        String jsonResponse = "src/main/resources/responses/unit.json";
//        ObjectMapper jsonReader = new ObjectMapper();
//        Object obj = jsonReader.readValue(new File(jsonResponse), Object.class);
        String payload = readJsonFile("responses/xml_test_response.xml");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("sessionId", "generatedSessionId");
        httpHeaders.set("Content-Type", MediaType.APPLICATION_XML_VALUE);
        return ResponseEntity.ok().headers(httpHeaders).body(payload);
    }

    @GetMapping("/paramCheck")
    public ResponseEntity<?> getParams() throws Exception {
        Session session = new Session("sessionId");
        RestTemplate restTemplate = new RestTemplate();

        return ResponseEntity.ok().body(session);
    }

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody Session session) throws Exception {
        Token token = new Token("token " + session.getSessionId());
        return ResponseEntity.ok(token);
    }

    @Autowired
    private UserService userService;

    @GetMapping("/page/example")
    public Page<TwitterUser> getUsers(Pageable pageable) {
        return userService.findPaginated(pageable);
    }

    @GetMapping("/cursor/example")
    public CursorPage<TwitterUser> getUsersCursor(@RequestParam(required = false) String cursor, @RequestParam(defaultValue = "10") int size) {
        return userService.findItems(cursor, size);
    }

    @GetMapping("/offset/example")
    public CursorPage<TwitterUser> getUsers(@RequestParam(defaultValue = "0") int offset,
                                            @RequestParam(defaultValue = "10") int limit) {
        return userService.findItemsOffset(offset, limit);
    }

    @GetMapping("/next/odata")
    public CursorPage<TwitterUser> getUsersWithNextLink(@RequestParam(defaultValue = "0") int offset,
                                            @RequestParam(defaultValue = "10") int limit) {
        return userService.findItemsOffset(offset, limit);
    }


    private static String readJsonFile(String filePath) throws IOException {
        // Load the JSON file from the resource folder
        ClassPathResource resource = new ClassPathResource(filePath);

        // Read the content of the JSON file into a byte array
        byte[] fileBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());

        // Convert the byte array to a string using the UTF-8 charset
        return new String(fileBytes, StandardCharsets.UTF_8);
    }

}
