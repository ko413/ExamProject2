package creature.character;

import creature.Character;
import creature.Creature;
import weapon.Weapon;

public  class Wizard extends Character{
    int mp;
    public Wizard(String name, int hp, int mp, Weapon weapon) {
        super(name, hp, weapon);
        this.mp = mp;
    }
    public void magic(Creature target) {
        int cost = this.getWeapon().getCost();
        if (this.mp < cost) {
            System.out.println("MPが足りない！");
            return;
        }
        this.mp -= cost;
        int damage = this.getWeapon().getDamage();
        System.out.println(this.getName() + "は" + this.getWeapon().getName() + this.getWeapon().attackMessage());
        System.out.println(target.getName() + "に" + damage + "のダメージを与えた！");
        target.setHp(target.getHp() - damage);
        if (target.getHp() < 0) {
            target.setHp(0);
        }
    }
    public void attack(Creature target) {
        System.out.println(this.getName() + "は石を投げた！");
        System.out.println(target.getName() + "に3のダメージを与えた！");
        target.setHp(target.getHp() - 3);
    }
    public void showStatus() {
        System.out.println(getName() + ":" + "HP" + getHp() + ",/MP" + this.mp);
    }

    public int getMp() {
        return mp;
    }
    public void setMp(int mp) {
        this.mp = mp;
    }
}
