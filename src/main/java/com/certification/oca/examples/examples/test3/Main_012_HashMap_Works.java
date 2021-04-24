package com.certification.oca.examples.examples.test3;


import java.util.HashMap;

public class Main_012_HashMap_Works {
    public static void main(String[] args) {

		/*
		 * fonctionnement :
		 *
		 * pour le p2 on n'a pas des problèmes car il va être enregistré dans un bucket différent des autres (p1,p3,p4,p5 qui ont le meme hashcode 1)
		 * pourquoi on doit implémenter la méthode hashCode meme si la jvm peut créer des hashCodes pour tous les objects :
		 * 		en implémentant le hashCode l instance devient dependante de ses attributes hashCode = 12*id+age; et donc si on a une autre instance qui a le meme id et age
		 * 		elle aura le meme hashCode
		 *
		 * les objets sont enregistrés dans des buckets sous la forme des Entry. chaque Entry contient la clé, la valeur, le hashCode de la clé et une référence vers le
		 * next entry
		 * 	static class Entry<K,V> implements Map.Entry<K,V>
 				{
     				final K key;
     				V value;
     				Entry<K,V> next;
     				final int hash;
     				........
 				}
		 *
		 */
        HashMap<Person, String> map = new HashMap<Person, String>();
        Person p1 = new Person(1, "ABC");
        Person p2 = new Person(2, "DEF");
        Person p3 = new Person(1, "XYZ");
        Person p4 = new Person(1, "PQR");
        Person p5 = new Person(1, "PQR");
        System.out.println("Adding Entries ....");
        map.put(p1, "ONE");
        map.put(p2, "TWO");
        map.put(p3, "THREE");
        /*
         * p3 a un hashcode égale à 1, donc elle sera enregistré dans le meme bucket que p1. mais avant on va comparer le p3 avec tous les objets dans la bucket pour voir
         * si il est égale à un autre objet dans ce cas non donc le p3 sera enregistré dans le meme bucket que p1
         */
        map.put(p4, "FOUR");
        /*
         * de meme
         */
        map.put(p5, "FIVE");
        /*
         * lors de la comparaison avec les autres objets dans la bucket (avec la methode equals) on trouve que le p5 est égale à p4 donc le p5 écrase le p4
         * map.get(p4) == map.get(p5) == FIVE
         */

        System.out.println("\nComplete Map entries \n" + map);

        System.out.println("\nAccessing non-collided key");
        System.out.println("Value = " + map.get(p2));
        /*
         * recherche dans les Entry du bucket a un hashCode correspond au hashCode de p2
         * identification du Entry
         * retourne de la valeur
         */
        System.out.println("\nAccessing collided key , map.get(p1)");
        System.out.println("Value  = " + map.get(p1));
        /*
         * recherche dans les Entry du bucket a un hashCode correspond au hashCode de p1
         * on a trop p3, p5
         * conflit de hashCode , donc on passe a l utilisation de la methode equals sur les clés des Entry
         * on returouve une seuls clé qui est égale à p1
         * retourner la valeur de p1
         */
        System.out.println("\nAccessing collided key ,  map.get(p4)");
        System.out.println("Value  = " + map.get(p4));
        System.out.println("\nAccessing collided key , map.get(p5) ");
        System.out.println("Value = " + map.get(p5));
    }
}

class Person {
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int hashCode() {
        System.out.println("called hashCode for =" + id + "." + name);
        return id;
    }

    public boolean equals(Object obj) {
        System.out.println("called equals on =" + id + "." + name
                + "  to compare with  = " + ((Person) obj).getId() + "."
                + ((Person) obj).getName());
        boolean result = false;
        if (obj instanceof Person) {
            if (((Person) obj).getId() == id
                    && ((Person) obj).getName().equals(name))
                result = true;
        }
        return result;
    }

    public String toString() {
        return id + " - " + name;
    }
}
