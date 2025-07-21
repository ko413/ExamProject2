package creature;

import weapon.Weapon;
public abstract class Character implements Creature {
    private String name;
    private int hp;
    private Weapon weapon;
    public Character(String name, int hp, Weapon weapon) {
        if (hp < 0) {
            throw new IllegalArgumentException("初期設定に誤りがあるため、キャラクターを生成できませんでした");
        }
        this.name = name;
        this.hp = hp;
        this.weapon = weapon;
    }
    public void die() {
        System.out.println(this.getName() + "は死んでしまった！");
    }
    public final boolean isAlive() {
        return this.hp > 0;
    }
    public void showStatus() {
        System.out.println(getName() + ":" + "HP" + getHp());
    }
    @Override
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHp() {
        return hp;
    }
    @Override
    public void setHp(int hp) {
        this.hp = Math.max(hp, 0);
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
