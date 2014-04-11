package com.wikia.webdriver.TestCases.VisualEditor.EntryPoint;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.SourceEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 *
 * Editor Entry Point Test on wiki that has
 * wgEnabledRTEExt = false, wgVisualEditorUI = true, wgForceVisualEditor = true
 * User Editor Preference is set to Default for Anon
 * VE-983 verify VE Editor is loaded when clicking Add Page from the contribution drop down
 * VE-983 verify VE Editor is loaded when clicking the main edit button on the top of the article
 * VE-983 verify VE Editor is loaded when clicking the red link in the article
 * VE-983 verify VE Editor is loaded when clicking the section edit link in the article
 * VE-983 verify VE Editor is loaded when using ?veaction=edit in the URL
 * VE-983 verify VE Editor is loaded on List namespace
 * VE-983 verify CK Editor is loaded on Category namespace
 * VE-983 verify Src Editor is loaded on Template namespace
 * VE-983 verify CK Editor is loaded when using ?action=edit in the URL
 */

public class RTEDisabledEditorEntryAnonTests extends NewTestTemplateBeforeClass {

	WikiBasePageObject base;
	String wikiURL;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
		base = new WikiBasePageObject(driver);
	}

	@Test(
		groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_001"}
	)
	public void RTEDisabledEditorEntryAnonTests_001_CreatePageEntry() {
		String articleName = base.getNameForArticle();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.createArticleInVEUsingDropdown(articleName);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_002"}
	)
	public void RTEDisabledEditorEntryAnonTests_002_MainEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_003"}
	)
	public void RTEDisabledEditorEntryAnonTests_003_RedlinkEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditorPageObject ve = article.openVEModeWithRedLinks(0);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_004"}
	)
	public void RTEDisabledEditorEntryAnonTests_004_SectionEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditorPageObject ve = article.openVEModeWithSectionEditButton(0);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_005"}
	)
	public void RTEDisabledEditorEntryAnonTests_005_URLEntry() {
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_006"}
	)
	public void RTEDisabledEditorEntryAnonTests_006_ListNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.listPage);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_007"}
	)
	public void RTEDisabledEditorEntryAnonTests_007_CategoryNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.categoryPage);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_008"}
	)
	public void RTEDisabledEditorEntryAnonTests_008_TemplateNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.templatePage);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_009"}
	)
	public void RTEDisabledEditorEntryAnonTests_009_actionEdit() {
		SourceEditModePageObject src =
			base.navigateToArticleEditPageSrc(wikiURL, base.getNameForArticle());
		src.verifySourceOnlyMode();
	}
}
