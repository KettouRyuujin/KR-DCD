package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class Decade_Blast extends AbstractGameEffect {

	private final int damage;
	private final DamageInfo.DamageType Type;
	private boolean start = true;
	private boolean attack = true;

	public Decade_Blast(int x, DamageInfo.DamageType Type) {
		this.damage = x;
		this.Type = Type;
		this.duration = 1.4F;//倒数时间
		this.startingDuration = 1.4F;//持续时间
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();

		if(this.duration < this.startingDuration && start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(80);//切换模型
			start = false;
		}


		if(this.duration < 0.9F && attack){
			for(int i = 0;i < 5; i++) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(AbstractDungeon.player, this.damage , this.Type), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
			}
			attack = false;
		}

		if (this.duration < 0.0F) {
			this.isDone = true;
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(1);//时间结束后切换回原本模型
		}
	}

	public void render(SpriteBatch sb) { }

	public void dispose() { }
}