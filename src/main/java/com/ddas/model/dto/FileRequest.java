package com.ddas.model.dto;

import java.util.List;

public record FileRequest(String name, String description, List<String> tags ) { }