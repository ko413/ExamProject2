package creature.character;

import creature.Character;
import creature.Creature;
import weapon.Weapon;
public  class Thief extends Character {
    private boolean guard;

    public Thief(String name, int hp, Weapon weapon) {
        super(name, hp, weapon);
    }
    public void attack(Creature target) {
        int damage = this.getWeapon().getDamage() * 2;
        System.out.println(this.getName() + "は素早く2回攻撃した！" + this.getWeapon().getName() + this.getWeapon().attackMessage());
        System.out.println(target.getName() + "に" + damage + "のダメージを与えた！");
        target.setHp(target.getHp() - damage);
        if (target.getHp() < 0) {
            target.setHp(0);
        }
    }

    public void guard() {
        this.guard = true;
    }

    public void setHp(int hp) {
        if (this.guard()) {
            System.out.println("しかし、" + this.getName() + "は攻撃を回避し、ダメージが入らなかった！");
            this.guard = false;
            return;
        }
        super.setHp(hp);
    }
}
