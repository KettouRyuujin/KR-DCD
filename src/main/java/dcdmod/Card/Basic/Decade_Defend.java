package dcdmod.Card.Basic;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.Rollpower;
import dcdmod.Vfx.Kuuga_roll;


public class Decade_Defend extends AbstractCustomCardWithType{
	
	public static final String ID = "Decade_Defend";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Decade_Defend.png";
	private static final int COST = 1;
	private static final int BLOCK_AMT = 5;
	private static final int UPGRADE_PLUS_BLOCK = 3;

	
	
	public Decade_Defend() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF,CardColorType.Decade);
		this.tags.add(DCDmod.RiderCard);
		this.baseBlock = BLOCK_AMT;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Rollpower(p, 1), 1));
		if(p.hasPower("KamenRideKuugaPower")){
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_roll(), 0F));
		}
		//AbstractDungeon.actionManager.addToBottom(new VFXAction(new DenO_backtodcd(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 2F));
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Decade_Defend();
    }
	
	@Override
    public boolean isDefend() {
        return true;
    }
	
	@Override
	public void optionDecade() {
		setBackgroundTexture("img/512/skill_decade.png", "img/1024/skill_decade.png");
	}

	@Override
	public void optionKuuga() {
		setBackgroundTexture("img/512/skill_kuuga.png", "img/1024/skill_kuuga.png");
	}

	@Override
	public void optionAgito() {
	    setBackgroundTexture("img/512/skill_agito.png", "img/1024/skill_agito.png");
	}

	@Override
	public void optionRyuki() {
	    setBackgroundTexture("img/512/skill_ryuki.png", "img/1024/skill_ryuki.png");
	}

	@Override
	public void optionFaiz() {
	    setBackgroundTexture("img/512/skill_faiz.png", "img/1024/skill_faiz.png");
	}

	@Override
	public void optionBlade() {
	    setBackgroundTexture("img/512/skill_blade.png", "img/1024/skill_blade.png");
	}

	@Override
	public void optionHibiki() {
		setBackgroundTexture("img/512/skill_hibiki.png", "img/1024/skill_hibiki.png");
	}

	@Override
	public void optionKabuto() {
	    setBackgroundTexture("img/512/skill_kabuto.png", "img/1024/skill_kabuto.png");
	}

	@Override
	public void optionDenO() {
	    setBackgroundTexture("img/512/skill_deno.png", "img/1024/skill_deno.png");
	}

	@Override
	public void optionKiva() {
	    setBackgroundTexture("img/512/skill_kiva.png", "img/1024/skill_kiva.png");
	}

	@Override
	public void optionNeutral() {
		setBackgroundTexture("img/512/skill_decade.png", "img/1024/skill_decade.png");
	}
	
	@Override
    public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
		}
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Decade_Defend");
        NAME = Decade_Defend.cardStrings.NAME;
        DESCRIPTION = Decade_Defend.cardStrings.DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.BASIC[0], DCDmod.BASIC_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.BASIC[6], DCDmod.BASIC_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.BASIC[cost], DCDmod.BASIC_P[cost]);
		}
	}

	
	
}
