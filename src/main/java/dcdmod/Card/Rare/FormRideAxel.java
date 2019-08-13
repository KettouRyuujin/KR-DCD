package dcdmod.Card.Rare;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dcdmod.DCDmod;
import dcdmod.Actions.EnterButtonAction;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Vfx.Axel_faiztoaxel;
import dcdmod.Vfx.Faiz_axelsounds;



public class FormRideAxel extends AbstractCustomCardWithType{
	
	public static final String ID = "FormRideAxel";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/FormRideAxel.png";
	private static final int COST = 2;
	
	
	public FormRideAxel() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF,CardColorType.Faiz);
		this.tags.add(DCDmod.RiderCard);
		this.tags.add(DCDmod.FormRide);
		this.exhaust = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		CardCrawlGame.sound.playA("formride", 0.0f);
		AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_faiztoaxel(), 5.7F));
		AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_axelsounds(), 0F));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, 3, false), 3));
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(!p.hasPower("KamenRideFaizPower")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		else if(EnterButtonAction.AxelForm) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[1];
		}
		return canUse;
	}
	
	
	@Override
    public AbstractCard makeCopy() {
        return new FormRideAxel();
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
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("FormRideAxel");
        NAME = FormRideAxel.cardStrings.NAME;
        DESCRIPTION = FormRideAxel.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = FormRideAxel.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.RARE[0], DCDmod.RARE_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.RARE[6], DCDmod.RARE_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.RARE[cost], DCDmod.RARE_P[cost]);
		}
	}

	
	
}
