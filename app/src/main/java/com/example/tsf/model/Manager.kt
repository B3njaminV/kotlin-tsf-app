package com.example.tsf.model

class Manager {

    private var listeContenu : MutableList<Contenu> = ArrayList<Contenu>();

    fun ajouter(contenu: Contenu){
        listeContenu.add(contenu)
    }
}