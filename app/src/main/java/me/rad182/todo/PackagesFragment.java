package me.rad182.todo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import me.rad182.todo.model.Task;

public class PackagesFragment extends Fragment {
    private List<Task> tasks;
    private RecyclerView tasksRecycleView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_packages, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tasks = new ArrayList<Task>();
        //Set up RecyclerView
        tasksRecycleView = (RecyclerView) view.findViewById(R.id.tasks_recycler_view);
        setupRecyclerView(tasksRecycleView);

        Button addButton = (Button) view.findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextTask = (EditText) getView().findViewById(R.id.edit_text_task);
                String itemText = editTextTask.getText().toString();
                if (itemText.isEmpty()) { return; }

                Task task = new Task();
                task.name = itemText;
                tasks.add(task);
                // clear textfield
                editTextTask.setText("");
            }
        });
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        final TaskAdapter adapter = new TaskAdapter();
        adapter.setCallback(new TaskAdapter.Callback() {
            @Override
            public void onItemClick(Task task) {
                // delete task
                tasks.remove(task);
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setTasks(tasks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}
