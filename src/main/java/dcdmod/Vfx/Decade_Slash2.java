package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;

public class Decade_Slash2 extends AbstractGameEffect {

	private boolean start1 = true;
	private AbstractCreature source, target;
	private Vector2 start;
	private final int damage;
	private int stage;

	Decade_Slash2(AbstractCreature source, AbstractCreature target, int x, float drawX, float drawY) {
		this.duration = 2.9F;//倒数时间
		this.startingDuration = 2.9F;//持续时间
		this.source = source;
		this.target = target;
		this.damage = x;
		this.start = new Vector2(drawX, drawY);
		this.stage = 0;
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();

		if(this.duration < this.startingDuration && start1) {
			final Decade Decade = (Decade) AbstractDungeon.player;
			Decade.Trickster(84);//中途切换模型
			String DECADE_SLASH_ATLAS = "img/char/DCD_Animation/decade/decade_slash3.atlas";
			String DECADE_SLASH_JSON = "img/char/DCD_Animation/decade/decade_slash3.json";
			new AbstractAnimation("decade_slash", DECADE_SLASH_ATLAS, DECADE_SLASH_JSON, 0.8f, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0f);
			AbstractAnimation decade_slash = AbstractAnimation.getAnimation("decade_slash");
			decade_slash.setMovable(false);
			decade_slash.state.setAnimation(0, "slash", false);
			start1 = false;
			stage ++;
		}


		if(this.duration < this.startingDuration -0.27F && stage == 1){
			CardCrawlGame.sound.playA("attack_slash", 0F);
			stage ++;
		}
		else if(this.duration < this.startingDuration -0.77F && stage == 2){
			CardCrawlGame.sound.playA("attack_slash", 0F);
			stage ++;
		}
		else if(this.duration < this.startingDuration -1.39F && stage == 3){
			CardCrawlGame.sound.playA("attack_slash", 0F);
			stage ++;
		}
		else if(this.duration < this.startingDuration -1.56F && stage == 4){
			CardCrawlGame.sound.playA("attack_slash", 0F);
			stage ++;
		}
		else if(this.duration < this.startingDuration -2.18F && stage == 5){
			for(int i = 0;i < 5; i++) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target,new DamageInfo(this.source, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
			}
			stage ++;
		}

		if (this.duration < 0.0F) {
			this.isDone = true;
			AbstractAnimation.clear("decade_slash");
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(1);//时间结束后切换回原本模型
			this.source.drawX = this.start.x;
			this.source.drawY = this.start.y;
		}
	}

	public void render(SpriteBatch sb) { }

	public void dispose() { }
}