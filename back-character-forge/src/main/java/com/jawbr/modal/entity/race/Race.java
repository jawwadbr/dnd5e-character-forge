package com.jawbr.modal.entity.race;

import com.jawbr.modal.util.Size;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "race")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "index_name", unique = true, nullable = false)
    private String indexName;

    @Column(name = "race_name", nullable = false)
    private String raceName;

    @Column(name = "speed", nullable = false)
    private int speed;

    @Column(name = "age_desc", columnDefinition = "TEXT", nullable = false)
    private String age_desc;

    @Column(name = "aligment", columnDefinition = "TEXT", nullable = false)
    private String aligment;

    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    private Size size;

    @Column(name = "size_desc", columnDefinition = "TEXT", nullable = false)
    private String size_desc;

    @Column(name = "language_desc", columnDefinition = "TEXT", nullable = false)
    private String language_desc;

    @Column(name = "url", nullable = false)
    private String url;

}
