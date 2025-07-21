public abstract class Monster implements Creature {
    private String name;
    private int hp;
    private char suffix;

    public Monster(String name, char suffix, int hp) {
        if (hp < 0) {
            throw new IllegalArgumentException("初期設定に誤りがあるため、キャラクターを生成できませんでした");
        }
        this.name = name;
        this.suffix = suffix;
        this.hp = hp;
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
        this.hp = hp;
    }

    public char getSuffix() {
        return suffix;
    }

    public void setSuffix(char suffix) {
        this.suffix = suffix;
    }
    public final boolean isAlive() {
        return this.hp > 0;
    }
    public void showStatus() {
        System.out.println(this.getName() + this.getSuffix() + ":HP" + this.getHp());
    }
}