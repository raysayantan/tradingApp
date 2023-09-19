package com.merchant.trading.trading.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Response {
    int status;
    String message;
}
