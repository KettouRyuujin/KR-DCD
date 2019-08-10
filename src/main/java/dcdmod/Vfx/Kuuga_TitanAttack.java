package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class Kuuga_TitanAttack extends AbstractGameEffect {

	private final int damage;
	private final int magicNumber;
	private int stage;
	private AbstractCreature target;

	public Kuuga_TitanAttack(AbstractCreature target, int damage, int number) {
		this.duration = 0.75F;//倒数时间
		this.startingDuration = 0.75F;//持续时间
		this.target = target;
		this.damage = damage;
		this.magicNumber = number;
	}

	public void update() {

		this.duration -= Gdx.graphics.getDeltaTime();

		if(this.duration < this.startingDuration && stage == 0) {
			if(AbstractDungeon.player.hasPower("RisingTitanPower") && AbstractDungeon.player.hasPower("RisingMightyPower")){
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(116);//切换模型
				AbstractDungeon.effectsQueue.add(new Kuuga_MonsterAttacked(this.target));
				this.duration += 0.75F;
				stage ++;
			}
			else{
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(117);//切换模型
				AbstractDungeon.effectsQueue.add(new Kuuga_Titan_MonsterAttacked(this.target));
			}
			AbstractDungeon.actionManager.addToBottom(new DamageAction(target,new DamageInfo(AbstractDungeon.player,this.damage, DamageInfo.DamageType.NORMAL)));
			stage ++;
		}
		else if(this.duration <this.startingDuration && stage == 2){
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(117);//切换模型
			AbstractDungeon.effectsQueue.add(new Kuuga_Titan_MonsterAttacked(this.target));
			AbstractDungeon.actionManager.addToBottom(new DamageAction(target,new DamageInfo(AbstractDungeon.player,this.damage, DamageInfo.DamageType.NORMAL)));
			stage ++;
		}

		if (this.duration < 0.0F) {
			if(AbstractDungeon.player.hasPower("RisingTitanPower")){
				for(int i = 0;i<3;i++) {
					AbstractDungeon.actionManager.addToTop(new DamageAction(AbstractDungeon.player,new DamageInfo(AbstractDungeon.player, this.magicNumber, DamageInfo.DamageType.THORNS)));
				}
			}
			else{
				AbstractDungeon.actionManager.addToTop(new DamageAction(AbstractDungeon.player,new DamageInfo(AbstractDungeon.player, this.magicNumber, DamageInfo.DamageType.THORNS)));
			}
			this.isDone = true;
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(4);//时间结束后切换回原本模型
		}
	}

	public void render(SpriteBatch sb) { }

	public void dispose() { }
}