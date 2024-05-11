package com.example.inventory.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Description;


import java.math.BigDecimal;

public record ProductRecordDto(@NotBlank String name, @NotNull BigDecimal price, @NotNull String description) {


}
