package com.practice.service;

import com.practice.exception.TaskNotFoundException;
import com.practice.model.Task;
import com.practice.repository.TaskRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepo repo;
    @InjectMocks
    private TaskService service;

    @Test
    void createTask() {
        //given
        Task task = new Task("Watch Tv","Watch Loki new Season",true);
        //when
        service.createTask(task);
        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);

        //then
        verify(repo).save(taskArgumentCaptor.capture());
        Task actual = taskArgumentCaptor.getValue();
        assertThat(actual.getTitle()).isEqualTo(task.getTitle());

    }

    @Test
    void getTask() throws TaskNotFoundException {
        //given
        Task task = new Task("Learn Java","Revise Collection",false);

        //when
        when(repo.findById(1L)).thenReturn(Optional.of(task));
        Task actual = service.getTask(1);

        //then
        assertThat(actual.getTitle()).isEqualTo(task.getTitle());
    }

    @Test
    void getTasks() {
        //given
        List<Task> expected = new ArrayList<>();
        expected.add(new Task("Cook","Cooking something to eat",false));
        expected.add(new Task("Exercise","30 min Exercise",true));

        //when
        when(repo.findAll()).thenReturn(expected);
        List<Task> actual = service.getTasks();

        //then
        verify(repo,times(1)).findAll();
        assertThat(actual).isNotNull();
    }

    @Test
    void updateTask() throws TaskNotFoundException {
        //given
        Task expected = new Task("Watch Tv","Watch Loki new Season",true);

        //when
        when(repo.findById(1L)).thenReturn(Optional.of(expected));
        service.updateTask(1L,true);

        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);

        //then
        verify(repo,times(1)).findById(1L);
        verify(repo,times(1)).save(taskArgumentCaptor.capture());
        Task actual = taskArgumentCaptor.getValue();
        assertThat(actual.getTitle()).isEqualTo(expected.getTitle());
    }

    @Test
    void deleteTask() {
        service.deleteTask(1L);

        verify(repo).deleteById(1L);
    }
}