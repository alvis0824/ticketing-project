package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO,Long> implements TaskService {
    @Override
    public TaskDTO save(TaskDTO task) {
        if(task.getId() == null){
            task.setId(Long.valueOf(map.size()+1));
        }
        if(task.getAssignedDate() == null){
            task.setAssignedDate(LocalDate.now());
        }
        if(task.getTaskStatus() == null){
            task.setTaskStatus(Status.OPEN);
        }
        return super.save(task.getId(),task);
    }

    @Override
    public TaskDTO findById(Long taskId) {
        return super.findById(taskId);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(TaskDTO task) {
        if(task.getTaskStatus()==null){
            task.setTaskStatus(findById(task.getId()).getTaskStatus());
        }
        if(task.getId() == null){
            task.setId(findById(task.getId()).getId());
        }
        if(task.getAssignedDate()==null){
            task.setAssignedDate(findById(task.getId()).getAssignedDate());
        }
        super.update(task.getId(),task);
    }

    @Override
    public void deleteById(Long taskId) {
        super.deleteById(taskId);
    }
}
