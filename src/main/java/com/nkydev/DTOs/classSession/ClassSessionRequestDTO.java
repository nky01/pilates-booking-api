package com.nkydev.DTOs.classSession;

import java.sql.Time;
import java.util.Date;

public record ClassSessionRequestDTO(
         Date date,
         Time startTime,
         Time endTime,
         Integer machines
) {}
