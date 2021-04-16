# fruit-practice
Esta aplicacion contiene un MainActivity el cual recibe una listView y gridVIew, las cuales deben llevar un adaptador para
renderizar nuestros datos de una forma especifica tanto en la listView como gridView. El adaptador debe sobreescribiir 4 metodos
obligatorios que son los siguientes:

getCount le va a decir al adaptador cuantas veces hay que iterar sobre una colección que le vamos a dar. 
El numero que le demos son los números que van a ser dibujados en ese listview

getIntent es para tener un ítem de esa colección. Nos da un ítem de esa colección a la que le doy una posición 

getItemId es para tener el ID del elemento en esa colección 

getView toma cada elemento (cada item) y es en este método donde se dibuja que vamos a hacer

Este mismo adaptador servira tanto para listView como para gridView.

Se sobrescribe el metodo onItemClickListener para decirle que hacer cuando se clickea una fruta.
Se sobrescribe el metodo onCreateOptionsMenu para crear un menú y el metodo onOptionsItemSelected para manejar que hacer
cuando se hace click en agregar una fruta, cambiar de listView o gridView.
Tambien se sobrescribe el metodo onCreateContextMenu para crear un menu cuando se mantiene presionada una fruta y onContextItemSelected
para manejar que hacer cuando se clickea en delete.


