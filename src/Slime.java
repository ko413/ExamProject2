public final class Slime extends Monster{
    public Slime(char suffix, int hp) {
        super("スライム", suffix, hp);
    }
    public void attack(Creature target) {
        System.out.println("スライム" + getSuffix() + "は体当たり攻撃！" + target.getName() + "に5のダメージを与えた！");
        target.setHp(target.getHp() - 5);
        if (target.getHp() < 0) {
            target.setHp(0);
        }
    }
}
