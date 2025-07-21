public  class Wizard extends Character{
    int mp;
    public Wizard(String name, int hp, int mp) {
        super(name, hp);
        this.mp = mp;
    }
    public void attack(Creature target) {
        System.out.println(this.getName() + "は火の玉を放った！" +target.getName() + "に3のダメージを与えた！");
        target.setHp(target.getHp() - 3);
        if (target.getHp() < 0) {
            target.setHp(0);
        }
        this.mp -= 1;
    }

}
