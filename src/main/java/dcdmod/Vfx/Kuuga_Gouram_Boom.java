package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Patches.AbstractAnimation;

public class Kuuga_Gouram_Boom extends AbstractGameEffect {

	private boolean start = true;
	private AbstractCreature target;


	Kuuga_Gouram_Boom(AbstractCreature target) {
		this.duration = 1.37F;//倒数时间
		this.startingDuration = 1.37F;//持续时间
		this.target = target;
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();

		if(this.duration < this.startingDuration && start) {
			String KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/gouram/gouram_boom.atlas";
			String KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/gouram/gouram_boom.json";
			new AbstractAnimation("kuuga_gouram_boom", KUUGA_ATTACKED_ATLAS, KUUGA_ATTACKED_JSON, 0.1f, this.target.drawX, Settings.M_H/2, this.target.hb_w, this.target.hb_h, 1.0f);
			AbstractAnimation kuuga_attacked =  AbstractAnimation.getAnimation("kuuga_gouram_boom");
			kuuga_attacked.setMovable(false);
			kuuga_attacked.state.setAnimation(0, "boom", false);
			CardCrawlGame.sound.playA("kuuga_boom", 0.0f);
			start = false;
		}

		if (this.duration < 0.0F) {
		AbstractAnimation.clear("kuuga_gouram_boom");
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb) { }

	public void dispose() { }
}