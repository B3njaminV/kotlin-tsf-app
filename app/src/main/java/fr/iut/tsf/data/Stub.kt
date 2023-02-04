package fr.iut.tsf.data

import fr.iut.tsf.model.Film

class Stub {
    fun filmStub(): MutableList<Film>{
        val films = mutableListOf<Film>();
        films.add(Film(1,"Le seigneur des anneaux","/home/leseigneurdesanneaux.jpg"));
        films.add(Film(2,"Le seigneur des anneaux 2","/home/leseigneurdesanneaux2.jpg"));
        films.add(Film(3,"Le seigneur des anneaux 3","/home/leseigneurdesanneaux3.jpg"));
        films.add(Film(4,"Le seigneur des anneaux 4","/home/leseigneurdesanneaux4.jpg"));
        return films;
    }
}