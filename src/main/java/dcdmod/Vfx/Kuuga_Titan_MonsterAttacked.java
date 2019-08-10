package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Patches.AbstractAnimation;

public class Kuuga_Titan_MonsterAttacked extends AbstractGameEffect {

	private final String id;
	private boolean start = true;
	private AbstractCreature target;
	public static int x;

	public Kuuga_Titan_MonsterAttacked(AbstractCreature target) {
		this.duration = 0.50F;//倒数时间
		this.startingDuration = 0.50F;//持续时间
		this.target = target;
		this.id = "titan_m_attacked" + x;
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();

		if(this.duration < this.startingDuration && start) {
			x += 1;
			String TITAN_M_ATTACKED_ATLAS;
			String TITAN_M_ATTACKED_JSON;
			TITAN_M_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/titan/titan_slash.atlas";
			TITAN_M_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/titan/titan_slash.json";
			new AbstractAnimation(this.id, TITAN_M_ATTACKED_ATLAS, TITAN_M_ATTACKED_JSON, 0.8f, this.target.drawX, this.target.drawY + this.target.hb_h/2, this.target.hb_w, this.target.hb_h, 1.0f);
			AbstractAnimation kuuga_attacked =  AbstractAnimation.getAnimation(this.id);
			kuuga_attacked.setMovable(false);
			kuuga_attacked.state.setAnimation(0, "slash", false);
			CardCrawlGame.sound.playA("titan_slash", 0.0f);
			start = false;
		}

		if (this.duration < 0.0F) {
			AbstractAnimation.clear(this.id);
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb) { }

	public void dispose() { }
}