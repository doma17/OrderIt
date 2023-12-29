package inu.amigo.orderIt.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class MenuGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;

    private int menuId;


    private int combId;

    @ManyToOne
    @JoinColumn
    private List<SaleTime> saleTimes = new ArrayList<>();

}
