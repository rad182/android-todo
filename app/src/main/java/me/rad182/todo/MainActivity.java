package me.rad182.todo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import me.rad182.todo.model.Task;

public class MainActivity extends AppCompatActivity {

    private List<Task> tasks;
    private RecyclerView tasksRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tasks = new ArrayList<Task>();
        //Set up RecyclerView
        tasksRecycleView = (RecyclerView) findViewById(R.id.tasks_recycler_view);
        setupRecyclerView(tasksRecycleView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onAddTask(View view) {
        EditText editTextTask = (EditText) findViewById(R.id.edit_text_task);
        String itemText = editTextTask.getText().toString();
        if (itemText.isEmpty()) { return; }

        Task task = new Task();
        task.name = itemText;
        tasks.add(task);
        // clear textfield
        editTextTask.setText("");
    }
}
