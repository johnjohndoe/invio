package de.tarent.nic.tracker.outlier;

import de.tarent.nic.entities.NicGeoPoint;
import de.tarent.nic.tracker.exception.NicTrackerException;
import de.tarent.nic.tracker.geopoint.XYPointFactory;
import de.tarent.nic.tracker.outlier.fixtures.CandidateFixtures;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CentroidMedianEliminatorTest {

    private CentroidMedianEliminator cme;

    @Before
    public void setup() {
        // variable setup configuration
        cme = new CentroidMedianEliminator(new XYPointFactory(), 1.5);
    }

    @Test
    public void testThatNoPointIsEliminatedWhenTheTotalAmountOfPointsIsToSmall() throws NicTrackerException {
        final Set<NicGeoPoint> geoPointsIn = CandidateFixtures.createCandidatesTooSmallAmount();

        final Set<NicGeoPoint> expected = CandidateFixtures.createCandidatesTooSmallAmountExpected();

        final Set<NicGeoPoint> geoPointsOut = cme.removeOutliers(geoPointsIn);

        assertEquals(expected, geoPointsOut);
    }

    @Test
    public void testThatTheOutlierIsRemovedWhenItIsObviousThatItIsAnOutlierWithVaryingGeoPoints() throws NicTrackerException {
        final Set<NicGeoPoint> geoPointsIn = CandidateFixtures.createCandidatesWithAnObviousOutlier();

        final Set<NicGeoPoint> expected = CandidateFixtures.createCandidatesWithAnObviousOutlierExpected();

        final Set<NicGeoPoint> geoPointsOut = cme.removeOutliers(geoPointsIn);

        assertEquals(expected, geoPointsOut);
    }

    @Test
    public void testThatTheOutlierIsRemovedWhenItIsObviousThatItIsAnOutlierWithTheOutlierAddedInTheMiddleOfTheMap() throws NicTrackerException {
        final Set<NicGeoPoint> geoPointsIn = CandidateFixtures.createCandidatesWithAnObviousOutlierInTheMiddleOfTheMap();

        final Set<NicGeoPoint> expected = CandidateFixtures.createCandidatesWithAnObviousOutlierInTheMiddleOfTheMapExpected();

        final Set<NicGeoPoint> geoPointsOut = cme.removeOutliers(geoPointsIn);

        assertEquals(expected, geoPointsOut);
    }

    @Test
    public void testThatGeoPointsPlacedInASquareHaveNoOutliers() throws NicTrackerException {
        final Set<NicGeoPoint> geoPointsIn = CandidateFixtures.createCandidatesPlacedInASquare();

        final Set<NicGeoPoint> expected = CandidateFixtures.createCandidatesPlacedInASquareExpected();

        final Set<NicGeoPoint> geoPointsOut = cme.removeOutliers(geoPointsIn);

        assertEquals(expected, geoPointsOut);
    }

    @Test
    public void testThatFourGeoPointsPlacedInASquareAndOnePlacedInTheMiddleHaveNoOutliers() throws NicTrackerException {
        final Set<NicGeoPoint> geoPointsIn = CandidateFixtures.createCandidatesPlacedInASquareAndOnePlacedInTheMiddle();

        final Set<NicGeoPoint> expected = CandidateFixtures.createCandidatesPlacedInASquareAndOnePlacedInTheMiddleExpected();

        final Set<NicGeoPoint> geoPointsOut = cme.removeOutliers(geoPointsIn);

        assertEquals(expected, geoPointsOut);
    }

    @Test
    public void testThatAnOutlierFarAwayFromGeoPointsPlacedInASquareIsDetectedProperly() throws NicTrackerException {
        final Set<NicGeoPoint> geoPointsIn = CandidateFixtures.createCandidatesPlacedInASquareAndOnePlacedFarAway();

        final Set<NicGeoPoint> expected = CandidateFixtures.createCandidatesPlacedInASquareAndOnePlacedFarAwayExpected();

        final Set<NicGeoPoint> geoPointsOut = cme.removeOutliers(geoPointsIn);

        assertEquals(expected, geoPointsOut);
    }

    @Test
    public void testRasterCoordinates() throws NicTrackerException {
        final Set<NicGeoPoint> geoPointsIn = CandidateFixtures.createCandidatesRasterCoordinates();

        final Set<NicGeoPoint> expected = CandidateFixtures.createCandidatesRasterCoordinatesExpected();

        final Set<NicGeoPoint> geoPointsOut = cme.removeOutliers(geoPointsIn);

        assertEquals(expected, geoPointsOut);
    }

    // TODO: What is tested?
    @Test
    public void test() throws NicTrackerException {
        final Set<NicGeoPoint> geoPointsIn = CandidateFixtures.createCandidatesTest();

        final Set<NicGeoPoint> expected = CandidateFixtures.createCandidatesTestExpected();

        final Set<NicGeoPoint> geoPointsOut = cme.removeOutliers(geoPointsIn);

        assertEquals(expected, geoPointsOut);
        //assertTrue(expected.containsAll(geoPointsOut));
        //assertTrue(geoPointsOut.containsAll(expected));
    }

    @Test
    public void testThat2PointsFarAwayFromAnother2PointsHaveNoOutliers() throws NicTrackerException {
        final Set<NicGeoPoint> geoPointsIn = CandidateFixtures.createCandidatesTwoPointsFarAwayFromAnotherTwoPoints();

        final Set<NicGeoPoint> expected = CandidateFixtures.createCandidatesTwoPointsFarAwayFromAnotherTwoPointsExpected();

        final Set<NicGeoPoint> geoPointsOut = cme.removeOutliers(geoPointsIn);

        assertEquals(expected, geoPointsOut);
    }

    @Test
    public void testThat2PointsFarAwayFrom3PointsNearToEachAreDetectedAsOutliers() throws NicTrackerException {
        final Set<NicGeoPoint> geoPointsIn = CandidateFixtures.createCandidatesTwoPointsFarAwayFromThreePointsNearToEachOther();

        final Set<NicGeoPoint> expected = CandidateFixtures.createCandidatesTwoPointsFarAwayFromThreePointsNearToEachOtherExpected();

        final Set<NicGeoPoint> geoPointsOut = cme.removeOutliers(geoPointsIn);

        assertEquals(expected, geoPointsOut);
    }

    @Test
    public void testThatOutliersFromARealMapAreDetected() throws NicTrackerException {
        final Set<NicGeoPoint> geoPointsIn = CandidateFixtures.createCandidatesOutliersFromARealMap();

        final Set<NicGeoPoint> expected = CandidateFixtures.createCandidatesOutliersFromARealMapExpected();

        final Set<NicGeoPoint> geoPointsOut = cme.removeOutliers(geoPointsIn);

        assertEquals(expected, geoPointsOut);
    }
}
