package dcdmod.Card.Special;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcdmod.DCDmod;
import dcdmod.Actions.EnterButtonAction;
import dcdmod.Actions.TurnTimer;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Vfx.Axel_CrimsonSmash_Timer;
import dcdmod.Vfx.Faiz_CrimsonSmash;
import dcdmod.Vfx.Faiz_FAR_SoundsAndAnimation;



public class CrimsonSmash extends AbstractCustomCardWithType{
	
	public static final String ID = "CrimsonSmash";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/FinalAttackRide_Faiz.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 1;

	
	
	public CrimsonSmash() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ENEMY,CardColorType.Faiz);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = ATTACK_DMG;
		exhaust = true;
		setBackgroundTexture("img/512/FAR.png", "img/1024/FAR.png");
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		if(EnterButtonAction.AxelForm) {
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_Timer(this.damage), 0F));
		}
		else {
			TurnTimer.StopBGM(false);
			CardCrawlGame.sound.playA("faiz_BGM2", 0.0f);
			CardCrawlGame.sound.playA("FAR", 0F);
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_CrimsonSmash(m.drawX, m.drawY, m, this.damage), 0F));
			if(!DCDmod.AnimationTrigger && p.hasPower("KamenRideFaizPower")) {
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_FAR_SoundsAndAnimation(p.drawX, p.drawY), 4.425F));
			}
			else {
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_FAR_SoundsAndAnimation(p.drawX, p.drawY), 2F));
			}
		}
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster arg0)
	{
		super.calculateCardDamage(arg0);
		if(AbstractDungeon.player.hasPower("BladeKickPower")) {
			this.damage += this.damage;
			this.isDamageModified = true;
		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new CrimsonSmash();
    }
	
	@Override
	public void optionDecade() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionKuuga() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionAgito() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionRyuki() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionFaiz() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionBlade() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionHibiki() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionKabuto() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionDenO() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionKiva() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionNeutral() {

	}
    
	@Override
    public void upgrade() {
		if (!this.upgraded) {
			setBackgroundTexture("img/512/FAR.png", "img/1024/FAR.png");
		}
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("CrimsonSmash");
        NAME = CrimsonSmash.cardStrings.NAME;
        DESCRIPTION = CrimsonSmash.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = CrimsonSmash.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.FAR[0], DCDmod.FAR[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.FAR[6], DCDmod.FAR[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.FAR[cost], DCDmod.FAR_P[cost]);
		}
	}

	
	
}
