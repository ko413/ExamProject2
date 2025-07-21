package creature.character;

import creature.Character;
import creature.Creature;
import weapon.Weapon;
public class Hero extends Character {

    public Hero(String name, int hp, Weapon weapon) {
        super(name, hp, weapon);
    }

    public void attack(Creature target) {
        int damage = this.getWeapon().getDamage();
        System.out.println(this.getName() + "は" + this.getWeapon().getName() + this.getWeapon().attackMessage());
        System.out.println(target.getName() + "に" + damage + "のダメージを与えた！");
        target.setHp(target.getHp() - damage);
        if (target.getHp() < 0) {
            target.setHp(0);
        }
    }
}
