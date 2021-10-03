package kr.co.manty.r2dbcdemo;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@RequiredArgsConstructor
@Table("Students")
public class Student {
    @Id
    private Long id;

    @NonNull
    private final String name;
}