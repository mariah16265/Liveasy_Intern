package com.postgrestest.liveasy_intern.services;

import java.util.List;
import java.util.UUID;
import com.postgrestest.liveasy_intern.entities.Load;

public interface LoadService {
    List<Load> getLoads();
    Load getLoad(UUID loadId);
    Load addLoad(Load load);
    Load updateLoad(Load load);
    Load deleteLoad(UUID loadId);
    List<Load> getLoadsByShipperId(UUID shipperId); // Add this line
}
