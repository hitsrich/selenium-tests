package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsKruxObject;

import org.apache.commons.lang3.tuple.Pair;
import org.testng.annotations.Test;

import java.util.List;


/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class TestKruxSegment extends NewTestTemplate {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "kruxRealTimeSegment",
      groups = {"KruxRealtimeSegment", "Ads"}
  )
  public void testRealTimeSegment(List<Pair> pages, String segmentId) {
    AdsKruxObject adsKruxObject = new AdsKruxObject(driver);
    for (Pair page : pages) {
      String url = urlBuilder.getUrlForPath((String) page.getLeft(), (String) page.getRight());
      adsKruxObject.getUrl(url);
      adsKruxObject.waitForKrux();
    }
    Assertion.assertStringContains(segmentId, adsKruxObject.getKruxSegments());
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "kruxStandardSegmentOasis",
      groups = {"KruxSegmentDesktop", "Ads"}
  )
  public void testStandardSegmentOasis(List<Pair> pages, String segment, boolean isPresent,
                                       String cookie) {
    testStandartSegment(pages, segment, isPresent, cookie);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "kruxStandardSegmentWikiaMobile",
      groups = {"KruxSegmentWikiaMobile", "Ads"}
  )
  public void testStandardSegmentWikiaMobile(List<Pair> pages, String segment, boolean isPresent,
                                             String cookie) {
    testStandartSegment(pages, segment, isPresent, cookie);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "kruxStandardSegmentMercury",
      groups = {"KruxSegmentMercury", "Ads"}
  )
  public void testStandardSegmentMercury(List<Pair> pages, String segment, boolean isPresent,
                                         String cookie) {
    testStandartSegment(pages, segment, isPresent, cookie);
  }

  private void testStandartSegment(List<Pair> pages, String segment, boolean isPresent,
                                   String cookie) {
    AdsKruxObject adsKruxObject = new AdsKruxObject(driver);
    adsKruxObject.setKruxUserCookie(cookie);
    for (Pair page : pages) {
      String url = urlBuilder.getUrlForPath((String) page.getLeft(), (String) page.getRight());
      adsKruxObject.getUrl(url);
      adsKruxObject.waitForKrux();
      PageObjectLogging.log("DEBUG kxsegs", adsKruxObject.getKxsegs(), true);
      PageObjectLogging.log("DEBUG kxkuid", adsKruxObject.getKxkuid(), true);
    }
    String segments = adsKruxObject.getKruxSegments();
    Assertion.assertEquals(segments.contains(segment), isPresent);
  }
}
