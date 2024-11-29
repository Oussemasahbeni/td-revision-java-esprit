package com.esprit;

import java.util.*;

public class Agence {
    private String nom;
    private ListVoitures vs;
    private Map<Client, ListVoitures> clientVoitureLoue = new HashMap<>();

    public Agence(String nom) {
        this.nom = nom;
        this.vs = new ListVoitures();
    }

    public void ajoutVoiture(Voiture v) throws VoitureException {
        this.vs.ajoutVoiture(v);
    }

    public void suppVoiture(Voiture v) throws VoitureException {
        this.vs.supprimeVoiture(v);
    }

    public void loueClientVoiture(Client cl, Voiture v) throws VoitureException {
        if (!vs.getVoitures().contains(v)) {
            throw new VoitureException("Voiture not available in the agency.");
        }
        vs.supprimeVoiture(v);
        clientVoitureLoue.computeIfAbsent(cl, k -> new ListVoitures()).ajoutVoiture(v);
    }

    public void retourClientVoiture(Client cl, Voiture v) throws VoitureException {
        ListVoitures lv = clientVoitureLoue.get(cl);
        if (lv == null || !lv.getVoitures().contains(v)) {
            throw new VoitureException("Client did not rent this car.");
        }
        lv.supprimeVoiture(v);
        vs.ajoutVoiture(v);
        if (lv.size() == 0) {
            clientVoitureLoue.remove(cl);
        }
    }

    public List<Voiture> selectVoitureSelonCritere(Critere c) {
        List<Voiture> result = new ArrayList<>();
        for (Voiture v : vs.getVoitures()) {
            if (c.estSatisfaitPar(v)) {
                result.add(v);
            }
        }
        return result;
    }

    public Set<Client> ensembleClientsLoueurs() {
        return clientVoitureLoue.keySet();
    }

    public Collection<ListVoitures> collectionVoituresLouees() {
        return clientVoitureLoue.values();
    }

    public void afficheLesClientsEtLeursListesVoitures() {
        for (Map.Entry<Client, ListVoitures> entry : clientVoitureLoue.entrySet()) {
            System.out.println("Client: " + entry.getKey());
            entry.getValue().affiche();
        }
    }

    public Map<Client, ListVoitures> triCodeCroissant() {
        Map<Client, ListVoitures> sortedMap = new TreeMap<>(Comparator.comparingInt(Client::getCode));
        sortedMap.putAll(clientVoitureLoue);
        return sortedMap;
    }

    public Map<Client, ListVoitures> triNomCroissant() {
        Map<Client, ListVoitures> sortedMap = new TreeMap<>(Comparator.comparing(Client::getNom));
        sortedMap.putAll(clientVoitureLoue);
        return sortedMap;
    }
}