package com.foo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.morphia.annotations.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
public class NodeMap {
	@Getter
	private Map<String, NodeRoutes> nodes = new HashMap<>();
	
	public NodeMap(GoalTreeNode<?>... nodes) {
		if (nodes == null) {
			return;
		}
		
		for (GoalTreeNode<?> node : nodes) {
			put(node);
		}
	}
	
	public GoalTreeNode<?> get(String nodeId) {
		return nodes.containsKey(nodeId) ? nodes.get(nodeId).getNode() : null;
	}
	
	public void put(GoalTreeNode<?> node) {
		nodes.put(node.getId(), new NodeRoutes(node));
	}
	
	public List<String> getChildrenIds(String nodeId){
		return nodes.containsKey(nodeId) ? nodes.get(nodeId).getChildrenIds() : null;
	}
	
	public List<String> getChildrenIds(GoalTreeNode<?> node){
		return getChildrenIds(node.getId());
	}
	
	public List<GoalTreeNode<?>> getChildren(GoalTreeNode<?> node){
		List<GoalTreeNode<?>> list = new ArrayList<>();
		getChildrenIds(node).forEach(childId -> list.add(get(childId)));

		return list;
	}
	
	public List<GoalTreeNode<?>> getChildren(String nodeId){
		List<GoalTreeNode<?>> list = new ArrayList<>();
		getChildrenIds(nodeId).forEach(childId -> list.add(get(childId)));

		return list;
	}
	
	
	public void addChild(GoalTreeNode<?> parent, GoalTreeNode<?> child) {
		if (!nodes.containsKey(parent.getId())) {
			put(parent);
		}
		
		addChild(parent.getId(), child);
	}
	
	public void addChild(String parentId, GoalTreeNode<?> child) {
		NodeMap.NodeRoutes routes = nodes.get(parentId);
		routes.getChildrenIds().add(child.getId());

		if (!nodes.containsKey(child.getId())) {
			put(child);
		}
	}

	public void addChild(GoalTreeNode<?> parent, GoalTreeNode<?> child, int index) {
		if (!nodes.containsKey(parent.getId())) {
			put(parent);
		}

		addChild(parent.getId(), child, index);
	}

	public void addChild(String parentId, GoalTreeNode<?> child, int index) {
		NodeMap.NodeRoutes routes = nodes.get(parentId);
		routes.getChildrenIds().add(index, child.getId());

		if (!nodes.containsKey(child.getId())) {
			put(child);
		}
	}
	
	public GoalTreeNode<?> getParent(String nodeId) {
		return nodes.containsKey(nodeId) ? get(nodes.get(nodeId).getParentId()) : null;
	}
	
	public void setParent(String nodeId, String parentId) {
		if (!nodes.containsKey(parentId) || !nodes.containsKey(nodeId)) {
			return;
		}
		
		nodes.get(nodeId).setParentId(parentId);
	}
	
	public Map<String, GoalTreeNode<? extends BaseGoalData>> allNodesByName() {
		return allNodesByName(false);
	}
	
	public Map<String, GoalTreeNode<? extends BaseGoalData>> allNodesByName(boolean includeSubNodes) {
		Map<String, GoalTreeNode<? extends BaseGoalData>> nodesByName = new HashMap<>();
		nodes.values().forEach(route -> {
			nodesByName.put(route.getNode().getName(), route.getNode());
			
			if (includeSubNodes && route.getNode() instanceof BaseModule) {
				((BaseModule<?>)route.getNode()).getNodeMap().nodes.values()
				.forEach(subRoute-> nodesByName.put(subRoute.getNode().getName(), subRoute.getNode()));
			}
		});

		return nodesByName;
	}
	
	public Collection<NodeRoutes> allNodeRoutes(){
		return nodes.values();
	}
	
	public void combineMaps(NodeMap mapToCombine) {
		this.nodes.putAll(mapToCombine.nodes);
	}
	
	@Data
	@Entity
	@NoArgsConstructor
	@RequiredArgsConstructor
	public static class NodeRoutes {
		@NonNull
		private GoalTreeNode<?> node;
		private String parentId;
		private List<String> childrenIds = new ArrayList<>();
	}
}
