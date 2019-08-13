package dcdmod.Card.Basic;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcdmod.DCDmod;
import dcdmod.Card.Common.KamenRideDecade;
import dcdmod.Card.KamenRide.*;
import dcdmod.Card.Uncommon.*;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;



public class KamenRide extends AbstractCustomCardWithType{
	
	public static final String ID = "KamenRide";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/KamenRide.png";
	private static final int COST = 1;
	private AbstractCard c = null;
	private CardGroup group = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
	private boolean KamenRideCard = false;
	
	public KamenRide() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF,CardColorType.Decade);
		this.tags.add(DCDmod.RiderCard);
		this.tags.add(DCDmod.KamenRide);
		exhaust = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		KamenRideCard = true;
		group.addToBottom(new Kabuto_s());
		group.addToBottom(new Hibiki_s());
		group.addToBottom(new Blade_s());
		group.addToBottom(new Faiz_s());
		group.addToBottom(new Ryuki_s());
		group.addToBottom(new Agito_s());
		group.addToBottom(new Kuuga_s());
		group.addToBottom(new Decade_s());
		AbstractDungeon.gridSelectScreen.open(group, 1, "选择1张获得", false, false, true, false);
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new KamenRide();
    }
	
	
	@Override
	public void optionDecade() {

	}

	@Override
	public void optionKuuga() {

	}

	@Override
	public void optionAgito() {

	}

	@Override
	public void optionRyuki() {

	}

	@Override
	public void optionFaiz() {

	}

	@Override
	public void optionBlade() {

	}

	@Override
	public void optionHibiki() {

	}

	@Override
	public void optionKabuto() {

	}

	@Override
	public void optionDenO() {

	}

	@Override
	public void optionKiva() {

	}

	@Override
	public void optionNeutral() {

	}
	
	@Override
    public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeBaseCost(0);
		}
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("KamenRide");
        NAME = KamenRide.cardStrings.NAME;
        DESCRIPTION = KamenRide.cardStrings.DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && KamenRideCard) {
			switch(AbstractDungeon.gridSelectScreen.selectedCards.get(0).cardID) {
			case "Decade_s":
				c = new KamenRideDecade();
				break;
			case "Kuuga_s":
				c = new KamenRideKuuga();
				break;
			case "Agito_s":
				c = new KamenRideAgito();
				break;
			case "Ryuki_s":
				c = new KamenRideRyuki();
				break;
			case "Faiz_s":
				c = new KamenRideFaiz();
				break;
			case "Blade_s":
				c = new KamenRideBlade();
				break;
			case "Hibiki_s":
				c = new KamenRideHibiki();
				break;
			case "Kabuto_s":
				c = new KamenRideKabuto();
				break;
			}
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
			c = null;
			KamenRideCard = false;
			AbstractDungeon.gridSelectScreen.selectedCards.clear();
			group.clear();
		}
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
