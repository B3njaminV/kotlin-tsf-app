package fr.iut.tsf.data

import fr.iut.tsf.data.entity.FilmEntity
import fr.iut.tsf.model.Film

class Stub {
    fun filmStub(): MutableList<FilmEntity>{
        val films = mutableListOf<FilmEntity>();
        films.add(FilmEntity(1,"Le seigneur des anneaux","/home/leseigneurdesanneaux.jpg",12.0));
        films.add(FilmEntity(2,"Le seigneur des anneaux 2","/home/leseigneurdesanneaux2.jpg",15.0));
        films.add(FilmEntity(3,"Le seigneur des anneaux 3","/home/leseigneurdesanneaux3.jpg",16.0));
        films.add(FilmEntity(4,"Le seigneur des anneaux 4","/home/leseigneurdesanneaux4.jpg",14.0));
        return films;
    }
}