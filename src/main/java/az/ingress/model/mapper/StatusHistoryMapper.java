package az.ingress.model.mapper;

import az.ingress.dao.entity.StatusHistoryEntity;
import az.ingress.model.dto.StatusHistoryDTO;

public class StatusHistoryMapper {
    public static StatusHistoryDTO buildStatusHistoryDTO(StatusHistoryEntity statusHistories){
        return StatusHistoryDTO.builder().
                status(statusHistories.getStatus()).
                createdAt(statusHistories.getCreatedAt()).
                updatedAt(statusHistories.getUpdatedAt()).
                creditId(statusHistories.getCredit().getId()).
                build();
    }
}
