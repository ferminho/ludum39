package com.alienshots.ludum.component;

import com.alienshots.ludum.Time;
import com.badlogic.ashley.core.Component;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DropComponent implements Component {
    private final Time.Timer appearTimer;
}
