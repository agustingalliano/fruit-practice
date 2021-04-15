package galliano.android.fruitworld.activities;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import galliano.android.fruitworld.R;
import galliano.android.fruitworld.adaptaters.MyAdapter;
import galliano.android.fruitworld.models.Fruit;

public class MainActivity extends AppCompatActivity {

    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private GridView gridView;
    private MyAdapter myAdapter;
    private List<Fruit> fruits;
    private int counter = 0;

    private int currentViewMode=0;

    private MenuItem ItemList;
    private MenuItem ItemGrid;

    static final int VIEW_MODE_LISTVIEW=0;
    static final int VIEW_MODE_GRIDVIEW=1;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fruits = getAllFruits();

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        stubList = findViewById(R.id.stub_list);
        stubGrid =  findViewById(R.id.stub_grid);

        //Inflate ViewStub before get view
        stubList.inflate();
        stubGrid.inflate();

        listView = findViewById(R.id.listView);
        gridView = findViewById(R.id.gridView);

        //Get current view in share references

        //SharedPreferences sharedPreferences = getSharedPreferences("ViewMode",MODE_PRIVATE);
        //currentViewMode = sharedPreferences.getInt("currentViewMode",VIEW_MODE_LISTVIEW);//Default is view listview

        //Register item click

        listView.setOnItemClickListener(onItemClick);
        gridView.setOnItemClickListener(onItemClick);

        stubList.setVisibility(View.VISIBLE);
        myAdapter = new MyAdapter(this, R.layout.list_item,fruits);
        listView.setAdapter(myAdapter);

        registerForContextMenu(gridView);
        registerForContextMenu(listView);

    }

    private void switchView(){
        if(VIEW_MODE_LISTVIEW==currentViewMode){
            //Escondemos grid View e icono de list view
            stubGrid.setVisibility(View.GONE);
            this.ItemList.setVisible(false);
            //Enseñamos listView e icono de grid view
            stubList.setVisibility(View.VISIBLE);
            this.ItemGrid.setVisible(true);

        }else {
            //Escondemos list View e icono de grid view
            stubList.setVisibility(View.GONE);
            this.ItemGrid.setVisible(false);
            //Enseñamos gridView e icono de list view
            stubGrid.setVisibility(View.VISIBLE);
            this.ItemList.setVisible(true);

        }
        setAdapters();
    }

    private void setAdapters(){
        if(VIEW_MODE_LISTVIEW==currentViewMode){
            myAdapter = new MyAdapter(this, R.layout.list_item,fruits);
            listView.setAdapter(myAdapter);
        }else{
            myAdapter = new MyAdapter(this, R.layout.grid_item,fruits);
            gridView.setAdapter(myAdapter);
        }
    }

    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(fruits.get(position).getOrigin().equals("Unknown")){
                Toast.makeText(getApplicationContext(),"Sorry, we don´t have many info about "+fruits.get(position).getName(),Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"The best fruit from "+fruits.get(position).getOrigin()+" is "+fruits.get(position).getName(),Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu,menu);
        this.ItemList = menu.findItem(R.id.item_list);
        this.ItemGrid = menu.findItem(R.id.item_grid);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                counter++;
                addFruit(new Fruit("Added nº"+counter,R.mipmap.ic_fruit,"Unknown"));
                return true;
            case R.id.item_grid:
                currentViewMode = VIEW_MODE_GRIDVIEW;
                //Switch View
                switchView();

                return true;
            case R.id.item_list:
                currentViewMode = VIEW_MODE_LISTVIEW;

                //Switch View
                switchView();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.fruits.get(info.position).getName());
        inflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected( MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId()==R.id.delete_item){
            this.deleteFruit(info.position);
            this.myAdapter.notifyDataSetChanged();
            return true;
        }
        else {
            return super.onContextItemSelected(item);
        }
    }

    private List<Fruit> getAllFruits (){
        return new ArrayList<Fruit>(){{
            add(new Fruit("Banana",R.mipmap.ic_banana,"Gran Canaria"));
            add(new Fruit("Strawberry",R.mipmap.ic_strawberry,"Huelva"));
            add(new Fruit("Orange",R.mipmap.ic_orange,"Sevilla"));
            add(new Fruit("Apple",R.mipmap.ic_apple,"Madrid"));
            add(new Fruit("Cherry",R.mipmap.ic_cherry,"Galicia"));
            add(new Fruit("Pear",R.mipmap.ic_pear,"Canaria"));
            add(new Fruit("Grape",R.mipmap.ic_grape,"Barcelona"));

        }};
    }

    private void addFruit (Fruit fruit){
        this.fruits.add(fruit);
        this.myAdapter.notifyDataSetChanged();
    }

    private void deleteFruit (int position){
        this.fruits.remove(position);
        this.myAdapter.notifyDataSetChanged();
    }

}
