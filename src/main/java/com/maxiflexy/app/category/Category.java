package com.maxiflexy.app.category;

import com.maxiflexy.app.common.BaseEntity;
import com.maxiflexy.app.todo.Todo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "CATEGORIES")
public class Category extends BaseEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Todo> todos;
}