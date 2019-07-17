package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;
import dcdmod.Patches.AbstractSummonedAnimation;

import com.badlogic.gdx.graphics.Color;

public class Hibiki_henshin_SoundsAndAnimation extends AbstractGameEffect {
	private float x;
	private float y;
	private Texture img = null;
	boolean HENSHIN1Start = true;
	boolean HENSHIN2Start = true;
	boolean HENSHIN3Start = true;
	boolean start = true;
	public static String HENSHIN1_ATLAS = "img/char/DCD_Animation/hibiki/hibiki_henshin_card.atlas";
	public static String HENSHIN2_ATLAS = "img/char/DCD_Animation/hibiki/hibiki_henshin_P.atlas";
	public static String HENSHIN3_ATLAS = "img/char/DCD_Animation/hibiki/hibiki_henshin.atlas";
	public static String JSON1 = "img/char/DCD_Animation/hibiki/hibiki_henshin_card.json";
	public static String JSON2 = "img/char/DCD_Animation/hibiki/hibiki_henshin_P.json";
	public static String JSON3 = "img/char/DCD_Animation/hibiki/hibiki_henshin.json";

	
	public Hibiki_henshin_SoundsAndAnimation(float x, float y) {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
			new AbstractSummonedAnimation("HIBIKI_HENSHIN1",HENSHIN1_ATLAS,JSON1, 1.0f, x , y, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
			new AbstractAnimation("HIBIKI_HENSHIN2",HENSHIN2_ATLAS,JSON2, 1.0f, x+5.0f , y+25.0f, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
			new AbstractAnimation("HIBIKI_HENSHIN3",HENSHIN3_ATLAS,JSON3, 1.0f, x , y-5.0f, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
			//HibikiAnimationAction.hibiki_normal.getClass();
		}

		this.x = x;
		this.y = y;
		this.duration = 4.33F;//倒数时间
		this.startingDuration = 4.33F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		AbstractAnimation HENSHIN =  null;
		AbstractSummonedAnimation HENSHIN1 = null;
		this.duration -= Gdx.graphics.getDeltaTime();
		if(this.duration < 4.0F){
			if(HENSHIN1Start) {
				HENSHIN1 = AbstractSummonedAnimation.getAnimation("HIBIKI_HENSHIN1");
				HENSHIN1.setMovable(false);
				HENSHIN1.state.setAnimation(0, "card", false);
				CardCrawlGame.sound.playA("kamenride", 0.0f);
				CardCrawlGame.sound.playA("hibiki_henshin", 0F);
				HENSHIN1Start = false;
			}
		}
		if(this.duration < 3.08F){
			if(HENSHIN2Start) {
				HENSHIN =  AbstractAnimation.getAnimation("HIBIKI_HENSHIN2");
				HENSHIN.setMovable(false);
				HENSHIN.state.setAnimation(0, "P", false);
				HENSHIN2Start = false;
			}
		}
		if(this.duration < 2.63F){
			if(HENSHIN3Start) {
				HENSHIN =  AbstractAnimation.getAnimation("HIBIKI_HENSHIN3");
				HENSHIN.setMovable(false);
				HENSHIN.state.setAnimation(0, "henshin", false);
				HENSHIN3Start = false;
			}
		}
		if(this.duration < 2.00F){
			this.isDone = true;
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(49);//切换模型
		}
	}

	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		sb.draw(this.img, this.x, this.y);
		if(start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(43);//切换模型
			start = false;
		}
	}

	public void dispose() {
	}
}