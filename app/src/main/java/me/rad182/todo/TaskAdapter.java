package me.rad182.todo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import me.rad182.todo.model.Task;

/**
 * Created by rad182 on 18/03/2016.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> tasks;
    private Callback callback;

    public TaskAdapter() {
        this.tasks = Collections.emptyList();
    }

    public TaskAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        final TaskViewHolder viewHolder = new TaskViewHolder(itemView);
        viewHolder.contentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onItemClick(viewHolder.task);
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        Context context = holder.nameTextView.getContext();
        holder.task = task;
        holder.nameTextView.setText(task.name);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public View contentLayout;
        public TextView nameTextView;
        public Task task;

        public TaskViewHolder(View itemView) {
            super(itemView);
            contentLayout = itemView.findViewById(R.id.layout_content);
            nameTextView = (TextView) itemView.findViewById(R.id.text_task_name);

        }
    }

    public interface Callback {
        void onItemClick(Task task);
    }
}
