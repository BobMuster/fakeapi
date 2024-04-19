package com.fakeapi.fakeapi.service;

import com.fakeapi.fakeapi.dto.twitter.TwitterUser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final List<TwitterUser>  users;
    {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = readJsonFile("responses/pagination_response.json");
            users = mapper.readValue(json, new TypeReference<List<TwitterUser>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CursorPage<TwitterUser> findItems(String cursor, int size) {
        int startIndex = cursor == null ? 0 : users.indexOf(users.stream().filter(user -> user.getId().equals(cursor)).findFirst().orElse(null)) + 1;
        int endIndex = Math.min(startIndex + size, users.size());
        List<TwitterUser> pageItems = users.subList(startIndex, endIndex);
        String nextCursor = endIndex < users.size() ? pageItems.get(pageItems.size() - 1).getId() : null;
        String nextItems = "http://localhost:8081/api/page/cursor/example?size=5&cursor=" + nextCursor;
        return new CursorPage<>(pageItems, nextCursor, nextItems);
    }

    public CursorPage<TwitterUser> findItemsOffset(int offset, int limit) {
        List<TwitterUser> list;
        // Calculate the toIndex carefully to avoid IndexOutOfBoundsException
        int toIndex = Math.min(users.size(), offset + limit);
        if (offset > toIndex) {
            list = Collections.emptyList(); // Return an empty list if the offset is beyond the data size
        } else {
            list = users.subList(offset, toIndex);
        }
        String nextItems = "http://localhost:8081/api/cursor/example?limit="+limit+"&offset=" + toIndex;
        list = users.subList(offset, toIndex);
        return new CursorPage<TwitterUser>(list, nextItems, users.size(), toIndex, limit);
    }

    public Page<TwitterUser> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<TwitterUser> list;

        if (users.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, users.size());
            list = users.subList(startItem, toIndex);
        }

        return new PageImpl<TwitterUser>(list, Pageable.unpaged(), users.size());
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
