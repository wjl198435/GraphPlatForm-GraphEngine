package com.td.graph

import com.td.graph.CONSTANT._
import org.apache.tinkerpop.gremlin.process.traversal.Order
import org.apache.tinkerpop.gremlin.structure.{Direction, Vertex}
import org.janusgraph.core._
import org.janusgraph.core.schema.{JanusGraphIndex, JanusGraphManagement}
import org.slf4j.LoggerFactory

/**
  * Created by wjl198435 on 5/7/2017.
  */
object CreateSchema {
  private val LOGGER = LoggerFactory.getLogger("CreateGraphSchema")
  def makeVertexPropertyKeyIndex(jGraph: JanusGraph, IndexName: String, propertyKey: String, dataType: Class[_],
                                 uniqueNameCompositeIndex: Boolean): Unit = {

    if (jGraph != null && !propertyKey.isEmpty) {
      val mgmt: JanusGraphManagement = jGraph.openManagement
      if (mgmt != null) {

        LOGGER.info(" Enter makeVertexPropertyKeyIndex create  IndexName " + IndexName + ":" + propertyKey)
        if (!mgmt.containsPropertyKey(propertyKey)) {
          LOGGER.info("makeVertexPropertyKeyIndex create " + propertyKey + " as not existing")
          val name: PropertyKey = mgmt.makePropertyKey(propertyKey).dataType(dataType).make
          LOGGER.info("makeVertexPropertyKeyIndex create " + propertyKey + "....... make is ok")

          //import org.janusgraph.core.schema.JanusGraphManagement
          //val nameIndexBuilder: JanusGraphManagement.IndexBuilder = mgmt.buildIndex("name", classOf[Nothing]).addKey(name)

          if (IndexName != null && IndexName.length > 1 && !mgmt.containsGraphIndex(IndexName)) {
            LOGGER.info("makeVertexPropertyKeyIndex:***************-index " + IndexName + " as not existing")
            val nameIndexBuilder = mgmt.buildIndex(IndexName, classOf[Vertex]).addKey(name)
            val namei: JanusGraphIndex = nameIndexBuilder.buildCompositeIndex
            LOGGER.info("makeVertexPropertyKeyIndex index  " + IndexName + ".......make is ok")
          } else {
            LOGGER.info("EXIT makeVertexPropertyKeyIndex:  " + IndexName + " is NULL OR EXISTING!!!!")
          }
        }
        else {
          LOGGER.info("EXIT makeVertexPropertyKeyIndex:" + propertyKey + "is existing")
        }
        mgmt.commit
      }

    }
  }




  def getVertexName(): String ={
    var vertexList:String=" "
    var i=0
    for(vertext<-vertexLabelList){
      i+=1
      if(i<vertexLabelList.length)
        {
          vertexList+=vertext+","
        }
      else{
        vertexList+=vertext+" "
      }

    }
    vertexList
  }


  def makePropertyKey(jGraph: JanusGraph, propertyKey: String, dataType: Class[_], cardinality: Cardinality): Unit = {

   // LOGGER.info(propertyKey+":"+dataType+":"+cardinality)
    if (jGraph != null && !propertyKey.isEmpty) {
      val mgmt: JanusGraphManagement = jGraph.openManagement
      if (mgmt != null) {
        if (!mgmt.containsPropertyKey(propertyKey)) {
          LOGGER.info("makePropertyKey:" + propertyKey + " as not existing")
          val property = mgmt.makePropertyKey(propertyKey).dataType(dataType).cardinality(cardinality).make
          LOGGER.info("makePropertyKey:" + propertyKey +":dataType:"+ dataType+" ...is ok")
        }

        mgmt.commit
      }
    }
  }


  def makeEdgeLabel(jGraph: JanusGraph, edgeLabelLabel: String, sig: String, multi: Multiplicity): Unit = {

    LOGGER.info(" Enter makeVertexPropertyKeyIndex create edgeLabelLabel: " + edgeLabelLabel + "--sig:" + sig + "--multi:" + multi)

    if (jGraph != null && !edgeLabelLabel.isEmpty) {
      val mgmt: JanusGraphManagement = jGraph.openManagement
      if (mgmt != null) {
        if (!mgmt.containsEdgeLabel(edgeLabelLabel)) {
          if (sig != null) {
            LOGGER.info("makeEdgeLabel :" + edgeLabelLabel + " as not existing" + "signature:" + sig + " Multiplicity:" + multi)
            val sigPropertyKey: PropertyKey = mgmt.getOrCreatePropertyKey(sig);
            val edgeLabel = mgmt.makeEdgeLabel(edgeLabelLabel).multiplicity(multi).signature(sigPropertyKey).make
            mgmt.buildEdgeIndex(edgeLabel, "battlesByTime", Direction.BOTH, Order.decr, sigPropertyKey)
            LOGGER.info("makeEdgeLabel :" + edgeLabelLabel + " is ok")
          }
          else {
            val edgeLabel = mgmt.makeEdgeLabel(edgeLabelLabel).multiplicity(multi).make()
          }
        }
        else {
          LOGGER.info("makeEdgeLabel:" + edgeLabelLabel + " is exsiting")
        }
        mgmt.commit
      }
    }
  }


  def makeVertexLabel(jGraph: JanusGraph, vertexLabel: String, partition: Boolean): Unit = {


    if (jGraph != null && !vertexLabel.isEmpty) {
      val mgmt: JanusGraphManagement = jGraph.openManagement
      if (mgmt != null) {

        if (mgmt.getVertexLabel(vertexLabel) == null) {
          LOGGER.info("makeVertexLabel :" + vertexLabel + "as not existing")

          if (partition == true) {
            val label = mgmt.makeVertexLabel(vertexLabel).partition().make
          }
          else {
            val label = mgmt.makeVertexLabel(vertexLabel).make
          }

          LOGGER.info("makeVertexLabel :" + vertexLabel + "is ok")
        }

        mgmt.commit

      }

    } else {
      LOGGER.info("makeVertexLabel :" + vertexLabel + " is exsiting ,Nothing to do")
    }
  }


  def create(jGraph: JanusGraph): Unit = {

    println("Beging Create eventPropertyKeysList.........")
    for (propKey <- eventPropertyKeysList) {
      println(propKey)
      propKey match {

        case CONSTANT.riskScore => makePropertyKey(jGraph, propKey, classOf[java.lang.Integer], Cardinality.SINGLE)
        case CONSTANT.eventOccurTime => makePropertyKey(jGraph, propKey, classOf[java.lang.Long], Cardinality.SINGLE)
        case CONSTANT.ctimeVertexProperty=>  makePropertyKey(jGraph, propKey, classOf[java.lang.Long], Cardinality.SINGLE)
        case _ => makePropertyKey(jGraph, propKey, classOf[java.lang.String], Cardinality.SINGLE)
      }
    }

    println("Beging Create vertexLabelList.........")
    for (vlb <- CONSTANT.vertexLabelList) {
      //println(vlb)
      vlb match {
        case CONSTANT.partnerCode => makeVertexLabel(jGraph, vlb, true)
        case _ => makeVertexLabel(jGraph, vlb, false)
      }

    }

    println("Beging Create vertexPropertyKeys.........")
    for (vertexPK <- vertexPropertyKeys) {
      println(vertexPK)
      vertexPK match {
        case CONSTANT.ctimeVertexProperty => makeVertexPropertyKeyIndex(jGraph, "", vertexPK, classOf[java.lang.Long], false);
        case CONSTANT.eventOccurTime => makeVertexPropertyKeyIndex(jGraph, "", vertexPK, classOf[java.lang.Long], false);
        case CONSTANT.tagVertexProperty => makeVertexPropertyKeyIndex(jGraph, vertexPK, vertexPK, classOf[java.lang.Boolean], false);
        case _ => makeVertexPropertyKeyIndex(jGraph, vertexPK, vertexPK, classOf[java.lang.String], false)
      }
    }


    println("Beging Create actionEdgeLabelList.........")
    for (action <- actionEdgeLabelList) {
      makeEdgeLabel(jGraph, action, CONSTANT.ctimeVertexProperty, Multiplicity.MULTI)
    }


    println("Beging Create relationEdgeLabelList.........")
    for (relation <- relationEdgeLabelList) {
      relation match {
        case CONSTANT.FATHER => makeEdgeLabel(jGraph, relation, null, Multiplicity.MANY2ONE)
        case CONSTANT.MATHER => makeEdgeLabel(jGraph, relation, null, Multiplicity.MANY2ONE)
        case CONSTANT.SPOUSE => makeEdgeLabel(jGraph, relation, null, Multiplicity.MANY2ONE)
        case _ => makeEdgeLabel(jGraph, relation, null, Multiplicity.MULTI)
      }
    }
  }


  def main(args: Array[String]) {

    println(getVertexName)
    val conf = "/Users/wjl198435/Downloads/Graph/janusgraph-0.2.0-hadoop2/conf/janusgraph-hbase.properties"
    val graph = JanusGraphFactory.open(conf)
//    //makeVertexPropertyKeyIndex(graph,"seqID4","seqID4",true)
//
    create(graph)
//
//    //makeEdgeLabel(graph,"seqIDE",null)
//    //    createSchema(JanusGraphInstance.getGraph)
//    //    JanusGraphInstance.getGraph.close()
//    //createSchema(conf)
//
    graph.close()
  }

}